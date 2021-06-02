import { configure, shallow } from "enzyme";
import PlayQuiz from "../pages/PlayQuiz/Layout";
import Adapter from "enzyme-adapter-react-16";

configure({adapter: new Adapter()});

it("Renders without crashing", () => {
    shallow(<PlayQuiz/>);
});

test("PlayQuiz component should have main layout", () => {
    const component = shallow(<PlayQuiz/>);
    expect(component.find('Fragment')).toHaveLength(1);
});
