import { configure, shallow } from "enzyme";
import Header from "../shared/Header";
import Adapter from "enzyme-adapter-react-16";

configure({adapter: new Adapter()});

it("Renders without crashing", () => {
    shallow(<Header/>);
});

test("Footer and header are correctly loaded", () => {
    const componentHeader = shallow(<Header/>);
    expect(componentHeader.find('.header-container')).toHaveLength(1);
});
